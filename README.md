# serverless-simple-sms

### Deployment

1. `mvn install`
2. `mvn package`
3. `serverless deploy`

### Usage
* `POST {API_GATEWAY_URL}` with following body:
```
    {
    	"message": "asasdaa",
    	"phoneNumber": "+905391234567",
    	"senderId": "Mojilala",
    	"smsType": "Transactional"
    }
```

# Details 
Each SMS message can contain up to 140 bytes, and the character limit depends on the encoding scheme. For example, an SMS message can contain:

160 GSM characters
140 ASCII characters
70 UCS-2 characters
