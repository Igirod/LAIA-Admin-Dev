package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.AddressDTO;
import us.kanddys.laia.modules.ecommerce.services.AddressService;

@Controller
public class AddressController {

   @Autowired
   private AddressService addressService;

   @QueryMapping("gAddressUId")
   public List<AddressDTO> getAddressByUserId(@Argument Long userId, @Argument Integer page) {
      return addressService.findAddressByUserId(page, userId);
   }

   @MutationMapping("dAddress")
   public Integer deleteAddress(@Argument Long addressId) {
      return addressService.deleteAddress(addressId);
   }

   @MutationMapping("uAddress")
   public Integer updateAddress(@Argument Long addressId, @Argument Optional<Long> userId, Optional<String> title,
         Optional<String> lng, Optional<String> lat, Optional<String> direction) {
      return addressService.updateAddress(addressId, userId, title, lng, lat, direction);
   }

   @MutationMapping("aAddress")
   public Long addAddress(@Argument Long userId, @Argument Optional<String> title, @Argument Optional<String> lng,
         @Argument Optional<String> lat, @Argument Optional<String> direction) {
      return addressService.addAddress(userId, title, lng, lat, direction);
   }
}
