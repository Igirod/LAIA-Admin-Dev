package us.kanddys.laia.modules.ecommerce.services.payment.method.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.services.payment.method.PayPalService;

/**
 * Este clase implementa las obligaciones de la interfaz PayPalService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class PayPalServiceImpl implements PayPalService {

   private final String paypalClientId = "";
   private final String paypalSecret = "";
   private final String tokenUrl = "https://api-m.sandbox.paypal.com/v1/oauth2/token";

   @Override
   public String getToken() {
      String requestBody = "grant_type=client_credentials";
      String bearerToken = null;
      try {
         HttpClient client = HttpClient.newHttpClient();
         HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create(tokenUrl))
               .header("Authorization", "Basic " + getBasicAuthHeader(paypalClientId, paypalSecret))
               .header("Content-Type", "application/x-www-form-urlencoded")
               .POST(HttpRequest.BodyPublishers.ofString(requestBody))
               .build();
         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
         JSONObject jsonObject = new JSONObject(response.body());
         bearerToken = jsonObject.getString("token_type") + " " + jsonObject.getString("access_token");
         return null;
      } catch (IOException | InterruptedException e) {
         e.printStackTrace();
      }
      return bearerToken;
   }

   /**
    * Méotodo privado que obtiene el header de autenticación encriptando el
    * clientId y el clientSecret en base64.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param clientId
    * @param clientSecret
    * @return String
    */
   private String getBasicAuthHeader(String clientId, String clientSecret) {
      String credentials = clientId + ":" + clientSecret;
      return new String(java.util.Base64.getEncoder().encode(credentials.getBytes()));
   }
}
