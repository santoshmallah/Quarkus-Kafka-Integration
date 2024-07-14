[kafka_data.txt](https://github.com/user-attachments/files/16215911/kafka_data.txt)

# Pan verifiact API

### http://localhost:8080/kafka/v1/panvalidation

Request
```json
{
    "pan": "DKIPM3G",
    "consent": "Y",
    "reason": "Reason for verifying PAN"
}
```
Resposne
```json
{
    "verification": "failed",
    "message": "PAN not found.",
    "id": "882624e4-36f7-4f6b-aca0-1f98ca5354e1",
    "traceId": "1-66936b4e-04b944765b7e6ec5628ae972"
}
```
# Mobile OTP API

### http://localhost:8080/kafka/v1/verifyMobile?mobileNumber=YOUR_MOBILE_NUMBER

# Verify OTP
```URL
http://localhost:8080/kafka/v1/mobileOtpValidation?mobileNumber=YOUR_MOBILE_NUMBER&otp=YOUR_OTP
```


# Data writen in file

```URL
http://localhost:8080/kafka/v1/processdata
```
Request
```json
{
    "panNumber":"ABC",
    "mobileNumber":1234,
    "otp":65566
}
```
Response
```
Data proccess Successfull in topic
```
### sample response data

![image](https://github.com/user-attachments/assets/104008bb-e3b3-4f05-a461-f60db4736302)

[Uploading kafka_data.txt…]Key: request, Value: {"panNumber":"ABC","mobileNumber":1234,"otp":65566}
()

# Kafka topic

![image](https://github.com/user-attachments/assets/628a12bf-214a-4406-8aa3-f8901c457a34)


