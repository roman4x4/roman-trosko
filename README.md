1. Applications to be installed on PC:
- jdk 1.8 
- maven 3.5.4
- appium-desktop 1.15.1
- AndroidSDK

2. GnuCash application:
- should be installed on device
- wizard should be completed with default choices
- accounts should be in default state (names not edited)

3. Best Buy API Playground service:
- start service

4. Configuration:
in \src\test\resources\environment\default.properties edit values if needed:
- udidAndroidDevice - android emulator/device udid, can be found by "adb devices" command in commind line
- bbApiBaseUrl - test API base url (if changed in step 3)
- appiumHubUrl - link to appium server hub (if changed from default one)
- appPackage, appActivity - please do not change

Running tests:
- start appium
- start command line
- run testmobile.bat to execute tests for task 2
- run testapi.bat to execute tests for task 3

Solution was tested on Windows 10 + Android 8.0 Samsung Galaxy S7 (developer mode enabled)