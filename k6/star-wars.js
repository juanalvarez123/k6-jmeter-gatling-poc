import http from 'k6/http';
import {check, sleep} from 'k6';

export default function () {
    let request = http.get('https://swapi.dev/api/people/1');

    // Assertions
    check(request, {
        'Response has the attribute "name"': (response) =>
            JSON.parse(response.body).hasOwnProperty('name'),
        'Character is Luke Skywalker': (response) =>
            JSON.parse(response.body).name === 'Luke Skywalker'
    });

    sleep(1);
}
