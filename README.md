# Serverless Simple SMS

Serverless service to send single SMS to the given phone number using Amazon SNS Transactional SMS service through just a POST request. 

## Installation

1. Run serverless install `--url https://github.com/mojilala/serverless-simple-sms` to install the service in your current working directory
2. Next up cd into the service with `cd serverless-simple-sms`
3. Ensure you have set up required credentials as described [here](https://github.com/serverless/serverless/blob/master/docs/providers/aws/guide/credentials.md)
4. `mvn install`
5. `mvn package`
6. Deploy with `serverless deploy`. You will see the service information displayed and end-point in it.

## Usage

* `POST https://******.execute-api.us-east-1.amazonaws.com/production/send` with following body (`senderId` and `smsType` are optional and default values of them are set below):
```
    {
    	"message": "enter text message here",
    	"phoneNumber": "+905391234567",
    	"senderId": "MojiLaLa",
    	"smsType": "Transactional"
    }
```
* `curl -X POST https://******.execute-api.us-east-1.amazonaws.com/production/send --data '{ "message": "enter text message here", "phoneNumber": "+905391234567", }'`

## Details 

### phoneNumber
When you send an SMS message, specify the phone number using the E.164 format. E.164 is a standard for the phone number structure used for international telecommunication. Phone numbers that follow this format can have a maximum of 15 digits, and they are prefixed with the plus character (+) and the country code. For example, a U.S. phone number in E.164 format would appear as +1XXX5550100.

### message
Each SMS message can contain up to 140 bytes, and the character limit depends on the encoding scheme. For example, an SMS message can contain:

160 GSM characters
140 ASCII characters
70 UCS-2 characters
If you publish a message that exceeds the size limit, Amazon SNS sends it as multiple messages, each fitting within the size limit. Messages are not cut off in the middle of a word but on whole-word boundaries. The total size limit for a single SMS publish action is 1600 bytes.

### messageType
(Optional ,default="Transactional") You may set one of the following:
* __Promotional__ – Noncritical messages, such as marketing messages. Amazon SNS optimizes the message delivery to incur the lowest cost.
* __Transactional__ – (default) Critical messages that support customer transactions, such as one-time passcodes for multi-factor authentication. Amazon SNS optimizes the message delivery to achieve the highest reliability.

### senderId
(Optional, default="MojiLaLa") For Sender ID, type a custom ID that contains up to 11 alphanumeric characters, including at least one letter and no spaces. The sender ID is displayed as the message sender on the receiving device. For example, you can use your business brand to make the message source easier to recognize.

Support for sender IDs varies by country. For example, messages delivered to U.S. phone numbers will not display the sender ID. For the countries that support sender IDs, see [Supported Regions and Countries](http://docs.aws.amazon.com/sns/latest/dg/sms_supported-countries.html).


## AWS services used

* Lambda
* API Gateway
* SNS 

## Developed by

* [Selim Fırat Yılmaz](https://github.com/mrsfy)
