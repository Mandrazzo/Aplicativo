import com.google.maps.GeoApiContext;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;

import java.util.concurrent.TimeUnit;

public class GoogleMapsRealTime {

    public static void main(String[] args) {
        // Necessário entrar na Google Cloud Console e colocar uma chave de API(To sem cartão de crédito)
        String apiKey = "SUA_CHAVE_DE_API";

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        // Coordenadas de origem e destino (pode ser alterado conforme necessário)
        LatLng origin = new LatLng(ORIGEM_LATITUDE, ORIGEM_LONGITUDE);
        LatLng destination = new LatLng(DESTINO_LATITUDE, DESTINO_LONGITUDE);

        // Realiza uma solicitação para obter as direções
        DirectionsApiRequest request = DirectionsApi.getDirections(context, origin.toString(), destination.toString());
        try {
            DirectionsResult result = request.await();

            if (result.routes != null && result.routes.length > 0) {
                DirectionsRoute route = result.routes[0];

                // Itera sobre os passos da rota
                for (int i = 0; i < route.legs.length; i++) {
                    for (int j = 0; j < route.legs[i].steps.length; j++) {
                        // Obtém informações do passo, como coordenadas
                        LatLng stepStartLocation = route.legs[i].steps[j].startLocation;
                        LatLng stepEndLocation = route.legs[i].steps[j].endLocation;
                        System.out.println("Passo " + (i * route.legs.length + j + 1) + ":");
                        System.out.println("De: " + stepStartLocation);
                        System.out.println("Para: " + stepEndLocation);
                        System.out.println("-----------------------------");

                        // Aguarda um tempo para simular "tempo real"
                        TimeUnit.SECONDS.sleep(5); // Espera 5 segundos antes de buscar o próximo passo
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
