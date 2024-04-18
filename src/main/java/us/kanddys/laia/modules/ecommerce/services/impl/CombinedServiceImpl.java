package us.kanddys.laia.modules.ecommerce.services.impl;

import java.sql.Time;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedShopDTO;
import us.kanddys.laia.modules.ecommerce.exception.MerchantNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Batch;
import us.kanddys.laia.modules.ecommerce.model.Order;
import us.kanddys.laia.modules.ecommerce.model.User;
import us.kanddys.laia.modules.ecommerce.model.Utils.CalendarDay;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.CalendarJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.DisabledDateJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.MerchantJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.CombinedService;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

/**
 * Esta clase implementa las obligaciones de la interface CombinedService.
 * 
 * @author Igirod0
 * @version 1.0.3
 */
@Service
public class CombinedServiceImpl implements CombinedService {

   @Autowired
   private MerchantJpaRepository merchantJpaRepository;

   @Autowired
   private CalendarJpaRepository calendarJpaRepository;

   @Autowired
   private OrderJpaRepository orderJpaRepository;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Autowired
   private ProductService productService;

   @Autowired
   private ImageProductService imageProductService;

   @Autowired
   private OrderProductJpaRepository orderProductJpaRepository;

   @Autowired
   private ProductDetailService productDetailService;

   @Autowired
   private BatchJpaRepository batchJpaRepository;

   @Autowired
   private DisabledDateJpaRepository disabledDateJpaRepository;

   @Override
   public CombinedShopDTO findCombinedShop(String slug, Optional<Long> userId) {
      Map<String, Object> merchant = merchantJpaRepository.findMerchantIdAndTitleAndAddress(slug);
      if (merchant == null)
         throw new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND);
      Long merchantId = merchant.get("id") == null ? null : Long.valueOf(merchant.get("id").toString());
      String merchantTitle = (merchant.get("title") == null ? null : merchant.get("title").toString());
      var products = productService.getProductsPaginated(1, merchantId, Optional.of(1));
      Order order = orderIfUserPresent(userId, merchantId);
      Map<String, Object> calendarData = calendarJpaRepository.findTypeAndDelayAndCalendarIdByMerchantId(merchantId);
      var firstShippingDate = findFirstShippingDate(merchantId, Long.valueOf(calendarData.get("id").toString()),
            calendarData.get("type").toString(), Integer.valueOf(calendarData.get("delay").toString()));
      return new CombinedShopDTO(merchantId, merchantTitle, products, order.getId(),
            orderProductJpaRepository.countByOrderId(order.getId()), order.getUserId(),
            firstShippingDate.get("firstShippingDate"),
            (firstShippingDate.get("batchId").equals("0") ? null : Long.valueOf(firstShippingDate.get("batchId"))),
            (firstShippingDate.get("from").toString().equals("null") ? null : firstShippingDate.get("from").toString()),
            (firstShippingDate.get("to").toString().equals("null") ? null : firstShippingDate.get("to").toString()),
            (merchant.get("address") == null ? null : merchant.get("address").toString()));
   }

   @Override
   public CombinedProductDTO findCombinedProduct(Long productId, String slug, Optional<Long> userId) {
      Map<String, Object> merchant = merchantJpaRepository.findMerchantIdAndTitle(slug);
      if (merchant == null)
         throw new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND);
      Long merchantId = merchant.get("id") == null ? null : Long.valueOf(merchant.get("id").toString());
      String merchantTitle = (merchant.get("title") == null ? null : merchant.get("title").toString());
      var images = imageProductService.getImagesProductByProductId(productId);
      Map<String, Object> calendarData = calendarJpaRepository.findTypeAndDelayAndCalendarIdByMerchantId(merchantId);
      var details = productDetailService.getProductDetailsByProductId(productId);
      var product = productService.getProductById(productId);
      var invoice = orderIfUserPresent(userId, merchantId);
      var firstShippingDate = findFirstShippingDate(merchantId, Long.valueOf(calendarData.get("id").toString()),
            calendarData.get("type").toString(), Integer.valueOf(calendarData.get("delay").toString()));

      return new CombinedProductDTO(merchantId, merchantTitle, product.getId(), product.getTitle(), product.getPrice(),
            product.getFrontPage(),
            images, details, product.getStock(), invoice.getId(),
            orderProductJpaRepository.countByOrderId(invoice.getId()),
            invoice.getUserId(),
            (userId.isPresent() && orderProductJpaRepository.countByOrderId(invoice.getId()) > 0)
                  ? (orderProductJpaRepository.existOrderProductsByOrderIdAndProductId(invoice.getId(),
                        productId) != null ? 1
                              : 0)
                  : 0,
            firstShippingDate.get("firstShippingDate"), merchantJpaRepository.findAddressByMerchantId(merchantId),
            (firstShippingDate.get("batchId").equals("0") ? null : Long.valueOf(firstShippingDate.get("batchId"))),
            (firstShippingDate.get("from").toString().equals("null") ? null : firstShippingDate.get("from").toString()),
            (firstShippingDate.get("to").toString().equals("null") ? null : firstShippingDate.get("to").toString()));
   }

   /**
    * Este método se encarga de buscar una factura por usuario y comercio, si no
    * existe crea una nueva factura.
    *
    * @author Igirod0
    * @version 1.0.2
    * @param userId
    * @param merchantId
    * @return Invoice
    */
   private Order orderIfUserPresent(Optional<Long> userId, Long merchantId) {
      Order order;
      if (userId.isPresent()) {
         order = orderJpaRepository.findOrderIdByUserIdAndMerchantIdAndStatus(userId.get(), merchantId,
               Status.INITIAL.toString());
         if (order == null) {
            order = orderJpaRepository.save(new Order(userId.get(), merchantId));
         }
      } else {
         User user = new User(true);
         order = orderJpaRepository.save(new Order(userJpaRepository.save(user).getId(), merchantId));
      }
      return order;
   }

   @Override
   public CombinedProductDetailDTO findCombinedProductDetail(Long productId, Optional<Long> invoiceId,
         Long merchantId) {
      Map<String, Object> calendarData = calendarJpaRepository.findTypeAndDelayAndCalendarIdByMerchantId(merchantId);
      Map<String, String> firstShippingDate = findFirstShippingDate(merchantId,
            Long.valueOf(calendarData.get("id").toString()), calendarData.get("type").toString(),
            Integer.valueOf(calendarData.get("delay").toString()));
      CombinedProductDetailDTO combinedProductDetailDTO = new CombinedProductDetailDTO(
            productJpaRepository.findStockByProductId(productId), invoiceId.isPresent()
                  ? (orderProductJpaRepository.existOrderProductsByOrderIdAndProductId(invoiceId.get(),
                        productId) != null ? 1
                              : 0)
                  : 0,
            merchantJpaRepository.findAddressByMerchantId(merchantId), firstShippingDate.get("firstShippingDate"),
            (firstShippingDate.get("batchId").equals("0") ? null : Long.valueOf(firstShippingDate.get("batchId"))),
            (firstShippingDate.get("from").toString().equals("null") ? null : firstShippingDate.get("from").toString()),
            (firstShippingDate.get("to").toString().equals("null") ? null : firstShippingDate.get("to").toString()),
            imageProductService.getImagesProductByProductId(productId),
            productDetailService.getProductDetailsByProductId(productId));
      return combinedProductDetailDTO;
   }

   /**
    * Este método se encarga de buscar la primera fecha de envío disponible
    * teniendo en consideracion el tipo y delay del calendario asociado al
    * comerciante.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param merchantId
    * @param calendarId
    * @param type
    * @param delay
    * @return Map<String, String>
    */
   private Map<String, String> findFirstShippingDate(
         Long merchantId, Long calendarId, String type, Integer delay) {
      // TODO: Tener en cosideracion los batches y las reservas.
      Date actuallyDate;
      Date endDate;
      Calendar calendar;
      List<String> disabledDates;
      Long batchId = null;
      Time from = null;
      Time to = null;
      List<Batch> batches = new ArrayList<Batch>();
      List<String> exceptionalDates;
      try {
         actuallyDate = DateUtils.getCurrentDate();
         calendar = Calendar.getInstance();
         String[] dateActuallySplit = DateUtils.convertDateToStringWithoutTime(calendar.getTime()).split("-");
         calendar.setTime(actuallyDate);
         endDate = DateUtils.convertStringToDateWithoutTime(
               YearMonth.of(Integer.valueOf(dateActuallySplit[0]), Integer.valueOf(dateActuallySplit[1])).atEndOfMonth()
                     .toString());
         disabledDates = disabledDateJpaRepository.findDisabedDatesByCalendarIdRange(
               DateUtils.changeDateFormat(actuallyDate),
               endDate, calendarId);
         while (disabledDates.contains(DateUtils.convertDateToStringWithoutTime(calendar.getTime()))) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
         }
         exceptionalDates = batchJpaRepository.findExceptionBatchesDatesByCalendarIdAndDateNotNull(calendarId,
               DateUtils.convertStringToDateWithoutTime(DateUtils.convertDateToStringWithoutTime(calendar.getTime())),
               endDate);
         if (exceptionalDates.contains(DateUtils.convertDateToStringWithoutTime(calendar.getTime()))) {
            batches = batchJpaRepository.findExceptionlBatchesByCalendarId(calendarId, actuallyDate, endDate);
         } else {
            batches = batchJpaRepository.findNormalBatchesByCalendarId(calendarId);
         }
      } catch (NumberFormatException e) {
         throw new RuntimeException("Error al convertir la fecha");
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha");
      }
      Map<String, Object> batchData = settingBatchAtributes(type, calendar, delay, batches, batchId, from, to);
      return Map.of("firstShippingDate", DateUtils.convertDateToStringWithoutTime(calendar.getTime()), "batchId",
            batchData.get("batchId").toString(), "from",
            batchData.get("from").toString(), "to",
            batchData.get("to").toString());
   }

   /**
    * Este método se encarga de setear los datos del batch disponible en cuanto
    * a la fecha actual, depediendo del tipo y delay del calendario.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param type
    * @param calendar
    * @param delay
    * @param batches
    * @param batchId
    * @param from
    * @param to
    */
   private Map<String, Object> settingBatchAtributes(String type, Calendar calendar, Integer delay, List<Batch> batches,
         Long batchId,
         Time from, Time to) {
      List<String> workingDays = CalendarDay.getDays(batches.stream().map(Batch::getDays).toList());
      if (type.equals("HR") || type.equals("MN")) {
         calendar.add(Calendar.HOUR, delay);
         for (Batch batch : batches) {
            LocalTime actuallyDateTime = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),
                  calendar.get(Calendar.MINUTE),
                  calendar.get(Calendar.SECOND));
            if (batch.getFrom().toLocalTime().isBefore(actuallyDateTime)
                  && batch.getTo().toLocalTime().isAfter(actuallyDateTime)
                  &&
                  String.valueOf(batch.getDays()).contains(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)))) {
               return Map.of("batchId", batch.getId(), "from", batch.getFrom(), "to",
                     batch.getTo());
            }
         }
         if (batchId == null) {
            System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
            for (String day : workingDays) {
               if (!day.equals(CalendarDay.getDayNumber(calendar.get(Calendar.DAY_OF_WEEK)))) {
                  calendar.add(Calendar.DAY_OF_YEAR, 1);
                  break;
               }
            }
            for (Batch batch : batches) {
               if (batch.getDays().toString().contains(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)))) {
                  return Map.of("batchId", batch.getId(), "from", batch.getFrom(), "to",
                        batch.getTo());
               }
            }
         }
      }
      if (type.equals("DY")) {
         calendar.add(Calendar.DAY_OF_YEAR, delay);
         for (Batch batch : batches) {
            if ((batch.getDays().toString().contains(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK))))) {
               return Map.of("batchId", batch.getId(), "from", batch.getFrom(), "to",
                     batch.getTo());
            }
         }
      }
      return Map.of("batchId", 0, "from", "null", "to", "null");
   }
}
