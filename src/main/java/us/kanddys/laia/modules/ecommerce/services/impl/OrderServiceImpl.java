package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoicePaymentDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderDTO;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.OrderNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.Order;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;
import us.kanddys.laia.modules.ecommerce.repository.OrderJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ReservationJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.MerchantJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceCodeService;
import us.kanddys.laia.modules.ecommerce.services.MailSenderService;
import us.kanddys.laia.modules.ecommerce.services.OrderService;
import us.kanddys.laia.modules.ecommerce.services.check.ProductCheckStockService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

@Service
public class OrderServiceImpl implements OrderService {

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Autowired
   private ProductCheckStockService productCheckStockService;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private OrderProductCriteriaRepository orderProductCriteriaRepository;

   @Autowired
   private ReservationJpaRepository reservationJpaRepository;

   @Autowired
   private InvoiceCodeService invoiceCodeService;

   @Autowired
   private InvoiceProductJpaRepository invoiceProductJpaRepository;

   @Autowired
   private OrderJpaRepository orderJpaRepository;

   @Autowired
   private OrderProductJpaRepository orderProductJpaRepository;

   @Autowired
   private MerchantJpaRepository merchantJpaRepository;

   @Autowired
   private MailSenderService mailSenderService;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private BatchJpaRepository batchJpaRepository;

   @Autowired
   private OrderCriteriaRepository orderCriteriaRepository;

   @Override
   public List<OrderDTO> findOrdersByMerchantIdAndOptionalParamsPaginated(Integer page, Long merchantId,
         Optional<String> userEmail, Optional<Status> status) {
      return orderCriteriaRepository.findOrdersPaginated(page, merchantId, userEmail, status).stream().map(t -> {
         try {
            return new OrderDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public OrderDTO createOrder(Long merchantId, Long userId) {
      try {
         return new OrderDTO(orderJpaRepository.save(new Order(userId, merchantId)));
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }

   @Override
   public OrderDTO findOrderById(Long orderId) {
      var order = orderJpaRepository.findById(orderId);
      if (order.isEmpty())
         throw new OrderNotFoundException(ExceptionMessage.ORDER_NOT_FOUND);
      try {
         Map<String, Object> map = merchantJpaRepository
               .findLatAndLngAndAddressByMerchantId(order.get().getMerchantId());
         order.get().setCount(
               orderProductJpaRepository.countByOrderId(order.get() == null ? 0 : order.get().getId()));
         var selectedOrderDTO = new OrderDTO(order.get());
         selectedOrderDTO.setMerchantDirection((String) map.get("address"));
         selectedOrderDTO.setMerchantLat((String) map.get("lat"));
         selectedOrderDTO.setMerchantLng((String) map.get("lng"));
         return selectedOrderDTO;
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateOrderMessage(Long invoiceId, String message) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      orderJpaRepository.updateMessageByOrderId(message, invoiceId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateOrderNote(Long invoiceId, String note) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      orderJpaRepository.updateNoteByOrderId(note, invoiceId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateOrderStatus(Long invoiceId, String status) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      orderJpaRepository.updateStatusByOrderId(status.toString(), invoiceId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoicePaymentDTO updateOrderVoucher(MultipartFile voucher, Long orderId, Long paymentId,
         String date, Long batchId,
         Long merchantId,
         Long userId, String addressLat, String addressLng, String addressDirection) {
      // var order = orderJpaRepository.findById(orderId);
      // if (order.isEmpty())
      // throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      // // * Creación de la orden definitiva.
      // var merchantData =
      // merchantJpaRepository.findEmailAndUserIdAndSlugByMerchantId(merchantId);
      // var userData = userJpaRepository.findUserNameAndLastNameAndEmailById(userId);
      // var batchData =
      // batchJpaRepository.findFromTimeAndToTimeById(order.get().getBatchId());
      // Invoice invoice = new Invoice(order.get(), (String)
      // merchantData.get("title"), merchantId,
      // // (String) userData.get("name"),
      // // (String) userData.get("last_name"), (String) userData.get("email"),
      // // (String) batchData.get("CAST(to_time AS CHAR)"), (String)
      // batchData.get("CAST(from_time AS CHAR)"));
      // invoice.setCode(invoiceCodeService.generateInvoiceCode(merchantId, orderId));
      // invoice.setAddressStreet(addressDirection);
      // invoice.setAddressLat(addressLat);
      // invoice.setAddressLng(addressLng);
      // invoice.setMerchantId(merchantId);
      // updateOrderPayment(orderId, paymentId, date, batchId, merchantId, userId,
      // voucher, invoice);
      // return new InvoicePaymentDTO(invoice.getVoucher(), invoice.getCode(),
      // invoice.getId());
      return null;
   }

   private void updateOrderPayment(Long orderId, Long paymentId, String date, Long batchId, Long merchantId,
         Long userId, MultipartFile voucher, Invoice invoice) {
      invoice.setStatus(Status.PENDING);
      // try {
      // // invoice.setReservation(DateUtils.convertStringToDate(date + " " +
      // // DateUtils.getCurrentTime()));
      // reservationJpaRepository.save(
      // new Reservation(null, merchantId, userId, batchId,
      // DateUtils.convertStringToDateWithoutTime(date),
      // (invoice.getType() == null ? null : invoice.getType())));
      // invoice.setCreatedAt(DateUtils.getCurrentDate());
      // } catch (ParseException e) {
      // throw new RuntimeException("Error al convertir la fecha");
      // }
      // var orderProductsIds = new ArrayList<OrderProductId>();
      // var newInvoice = invoiceJpaRepository.save(invoice);
      // invoiceJpaRepository.updateVoucherByInvoiceId(
      // firebaseStorageService.uploadFile(voucher, "invoice-voucher-" +
      // newInvoice.getId(), "vouchers"),
      // newInvoice.getId());
      // List<InvoiceProduct> listInvoiceProducts =
      // orderProductCriteriaRepository.findOrderProductsByOrderId(orderId)
      // .stream().map(t -> {
      // orderProductsIds.add(new OrderProductId(t.getProduct().getId(), orderId));
      // productCheckStockService.checkStock(t.getProduct().getId(),
      // t.getProduct().getStock(), t.getQuantity());
      // return new InvoiceProduct(t, newInvoice.getId());
      // }).collect(Collectors.toList());
      // // * Guardado de la orden y sus productos.
      // invoiceProductJpaRepository.saveAll(listInvoiceProducts);
      // // ! Eliminamos los datos asociados a la factura anterior.
      // orderProductCriteriaRepository.deleteProductsByOrderId(orderId);
      // orderJpaRepository.deleteById(orderId);
      // try {
      // mailSenderService.sendUserOrder(new
      // MailDTO(userJpaRepository.findEmailByUserId(userId), "Invoice - Detail",
      // "user", "", newInvoice.getId()));
      // mailSenderService.sendUserOrder(new
      // MailDTO(merchantJpaRepository.findEmailByMerchantId(merchantId),
      // "New sale - Detail", "merchant", "", newInvoice.getId()));
      // } catch (MessagingException e) {
      // throw new RuntimeException("Error al enviar el correo");
      // }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateOrderAddress(Long orderId, Optional<String> direction, Optional<String> lat,
         Optional<String> lng, Optional<Integer> addressNumber, Optional<String> type) {
      if (invoiceJpaRepository.existsById(orderId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      orderJpaRepository.updateAddressByOrderId(direction.orElse(null), lat.orElse(null), lng.orElse(null), orderId,
            addressNumber.orElse(null), type.orElse(null));
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateOrderReservation(Long orderId, String date, Long batchId) {
      if (invoiceJpaRepository.existsById(orderId)) {
         orderJpaRepository.updateReservationAndBatchIdByOrderId(date, batchId, orderId);
         return 1;
      } else
         return 0;
   }
}
