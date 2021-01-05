# InsatgramLoginHelper

Goal is to implement Instagram Soical Login

## Using this library you can implement social login.

*Steps To Implement Login.<br>
-Create an app on facbook developer console.<br>
-Look for Instagram login.<br><br>

*After creating App fetch following things:<br><br>
-Client-Id<br>
-Client-Secret-id<br>
-Callback Url

## Usage
Gradle dependency
```groovy
dependencies {
   implementation 'com.github.aahanverma00710:InstagramLogin:1.0'
}
```

### Sample Code

Java:
```
LoginOptions options = new LoginOptions.init()
                .setClientId(ClientId)
                .setClientSecret(Client-SecretId)
                .setRedirectionUrl(Callback-Url)
                .build(context)
 
 InstagramLoginHelper.doLogin(context, options)
 ```
Kotlin:

```kotlin
val options = LoginOptions.init()
                .setClientId(ClientId)
                .setClientSecret(Client-SecretId)
                .setRedirectionUrl(Callback-Url)
                .build(context)
 
 InstagramLoginHelper.doLogin(context, options)
 
```

## Note
All the funconality is in WebView and only returns Name & SocialId(unique id used for login) of the user.
