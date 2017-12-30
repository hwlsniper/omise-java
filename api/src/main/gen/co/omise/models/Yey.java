package co.omise.models;

import java.lang.String;
import javax.annotation.Generated;

@Generated("co.omise.generators.ModelGenerator")
public class Yey {
    public String hello() {
        // ---
        openapi: 3.0.0
        info:
          title: Omise API
          version: 1.0
        definitions:
          Account:
            properties:
              email:
                type: string
                format: email
              currency:
                type: string
          Balance:
            properties:
              available:
                type: number
                format: int64
              total: long
                type: integer
                format: int64
              currency: string
                type: integer
                format: int64
        return "Hello World";
    }
}
