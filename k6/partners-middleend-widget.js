import http from 'k6/http';
import {check, sleep} from 'k6';

export default function () {
  let userId = '729054671';
  let siteId = 'MLB';
  let queryParameters = 'user_id=' + userId + '&site_id=' + siteId;

  let xAuthToken = '34ef487b95586bd3435e880f0a15f541ac5efed46b473d1fa78b68bb4ab81c51';

  // Pruebas con Widget (Nativo)
  let userAgent = 'MercadoLibre-Android/10.555.0';

  let params = {
    headers: {
      'x-auth-token': xAuthToken,
      'User-Agent': userAgent
    }
  }

  let request = http.get(
      'https://testbeta_partners-middleend.furyapps.io/content_widget?'
      + queryParameters,
      params);

  // Assertions
  check(request, {
    'Response has empty elements': (response) =>
        !JSON.parse(response.body).elements.length,
    'level in event_data exists': (response) =>
        JSON.parse(response.body).event_data.hasOwnProperty('level'),
    'has_divider in header having true value': (response) =>
        JSON.parse(response.body).header.has_divider === true
  });

  // Pruebas con Widget (Mobile)
  let xClientName = 'mobile';

  let params2 = {
    headers: {
      'x-auth-token': xAuthToken,
      'x-client-name': xClientName
    }
  }

  let request2 = http.get(
      'https://testbeta_partners-middleend.furyapps.io/content_widget?'
      + queryParameters,
      params2);

  // Assertions
  check(request2, {
    'Title has value': (response) =>
        JSON.parse(response.body).title === 'Benefícios do Mercado Pontos',
    'gradient_color in picture in elements exists': (response) =>
        JSON.parse(response.body).elements[1].picture.hasOwnProperty(
            'gradient_color'),
    'label in title in elements has value': (response) =>
        JSON.parse(response.body).elements[1].title.label === '40% OFF'
  });

  sleep(1);
}
