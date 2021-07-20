# https-xsd-call

-- To use the classpath XSD that will eliminate any http call whatsoever, try the camel-war-classpath-xsd project.

- The camel-spring-xx.jar already contains the XSD so, we don't need to refer it from the internet, simply use the following in the schemalocation:

`http://camel.apache.org/schema/spring classpath:camel-spring.xsd`

-- To use the https to fetch the XSD, try the camel-war-https-xsd project

- First fetch the correct certificate in PEM format

`openssl s_client -showcerts -connect camel.apache.org:443 -servername camel.apache.org < /dev/null | openssl x509 -outform PEM > apache.crt`
- Then import this to your truststore (if the truststore doesn't exist, it will be created after this command)

`keytool -import -file apache.crt -alias apache -keystore storename.keystore -storepass password`

- Then add the following system properties to the server configuration (standalone*.xml)
```
<system-properties>
        <property name="javax.net.ssl.trustStore" value="/abosulute/path/to/test.keystore"/>
        <property name="javax.net.ssl.trustStorePassword" value="password"/>
    </system-properties>language
```
-- One caveat with fetching the XSD from https is that the certificate you just imported has a limited validity and you'll have to import a new certificate after it expires so, using the classpath XSD appears to be the better option here.
```
Owner: CN=*.apache.org
Issuer: CN=R3, O=Let's Encrypt, C=US
Serial number: 449c0e02701697b351c3261089b16c1dee7
Valid from: Wed Jun 23 01:13:39 IST 2021 until: Tue Sep 21 01:13:38 IST 2021

```