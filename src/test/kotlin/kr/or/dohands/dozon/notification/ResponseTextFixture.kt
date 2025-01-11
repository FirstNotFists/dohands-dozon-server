package kr.or.dohands.dozon.notification

object ResponseTestFixture {

    const val RECEIPT_ID_1 = "645c0c84-1938-4dee-8c5f-b41076b0930c"

    const val PUSH_SEND_OK_SINGLE_RESPONSE =
        """
          {
              "data": [
                  {
                      "status": "ok",
                       "id": "caa6fe6e-85d8-456c-bf57-e8cdc3fcb137"
                  }
              ]
          }
          """

    const val GET_RECEIPT_OK_SINGLE_RESPONSE =
        """
          {
              "data": {
                  "caa6fe6e-85d8-456c-bf57-e8cdc3fcb137": {
                      "status": "ok"
                  }
              }
          }
          """

    const val RECEIPT_ID_2 = "0d44e896-1a04-4409-a56e-a7383641cdb7"

    const val RECEIPT_ID_3 = "0d44e896-1a04-4409-a56e-a7383641cdb7"

    const val PUSH_SEND_OK_MULTIPLE_RESPONSE =
        """
          {
              "data":[
                  {
                      "status":"ok",
                      "id":"0d44e896-1a04-4409-a56e-a7383641cdb7"
                  },
                  {
                      "status":"ok",
                      "id":"d6d93f26-a037-44a4-844d-c5c458844e9b"
                  }
              ]
          }
          """

    const val GET_RECEIPT_OK_MULTIPLE_RESPONSE =
        """
          {
              "data":{
                  "0d44e896-1a04-4409-a56e-a7383641cdb7":{
                      "status":"ok"
                  },
                  "d6d93f26-a037-44a4-844d-c5c458844e9b":{
                      "status":"ok"
                  }
              }
          }
          """

    const val PUSH_SEND_VALIDATION_ERROR_RESPONSE =
        """
          {
              "errors":[
                  {
                      "code":"VALIDATION_ERROR",
                      "message":"[0].data must be of type object.",
                      "isTransient":false,
                      "requestId":"96995d9b-1530-48ad-8501-7410acd9d2c6"
                  }
              ]
          }
          """
}
