# Roman Trosko Home Challenge 
# Task 1
Monefy is  finance management tool for iOS, Android and Windows Phone platforms. 


## I. Testing areas and types:
1. Business logic, feature testing
2. Mobile specific testing 
3. UI testing on different screen sizes and resolutions + split view
4. Performance testing: memory, battery consumption, application speed and responsiveness
5. Compatibility testing
6. Localization testing
7. Multiuser/device access to data
8. Version upgrades testing
9. Adherence to Apple Human Interface Guideline and Android Design Guidelines, Accessibility testing

###1 Business logic, feature testing

**Header Navigation**

- Displaying header text based on selected account filter
- Header: Transfer button calls new transfer screen
- Left menu button: calling/hiding left menu by swiping or clicking icon
- Right menu, calling/hiding left menu by swiping or clicking icon

**Left side menu**

- Filtering accounts
- Filtering periods

**Main menu**

- Periods, swiping periods forward and back for each period range
- Dashboard view, check expense icons displayed correctly
- Clicking icon open expense entry screen with default category selected and current date in case week, month, year, all filter selected
- If specific day selected clicking icon open expense entry screen with default category selected and filtered date in case day filter selected
- Long click on sector shows expense amount and name
- click on sector opens balance page with selected secot catgory expanded

**Expense button**

- clicking button open expense entry screen with no category selected and current date in case week, month, year, all filter selected
- clicking button open expense entry screen with no category selected and filtered date in case day filter selected            

**Income button**

- clicking icon open income entry screen with no category selected and current date in case week, month, year, all filter selected            
- clicking icon open income entry screen with no category selected and current date in case week, month, year, all filter selected            

**Balance screens**

- shows balance for selected period/account
- shows all categories that appeared in current period within account
- each category can be expanded
- clicking on item opens edit page
	
**Expence entry screen**

- date picker
- account selector
- amount calculator
- note field validations
- category selector

**Right side menu**

- Categories: clicking opens edit category page
for current category editing name/deleting/disabling/enabling/merging categories
- Account: shows balance per account/period
- Account: allows adding/editing account currency/ rate/initial balance/date/type 

**Settings**

- language
- default currency can be changed and saved
- calendar setting
- Passcode sets passcode
- Review link
- About
- Privacy policy opens page in correct language

**Data**

- Export to csv, check csv
- Data backup/restore (disk out of space, corrupted file, compatibility with previous versions scenarios).
- Check backup file and current save file are encrypted.
- Dropbox/GoogleDrive synchronization (compatibility with previous versions)
- Clear data cleras local data

**Analytics**

- analytics setting applied, check data sent/not sent after setting this options

**Calculations**

- check calculations are correct for different currencies, based on different data set

###2 Mobile specific testing
Application has limited access to mobile device services or operation system capabilities (e.g. location, notifications, sound, camera, sensors, background tasks, audio, etc). To be tested:

- saving files to system storage based on access level
- network interruptions, slow network behavior, suspending application (sending to background, closing) during network operation
- system interruptions (sending to background, closing) for each activity, including animations
- Checking that password is needed if application restores after interruption (need to know requirement)

###3-5 UI, Performance, Compatibility testing
As application does not access many OS capabilities no extended testing on real devices required. Still it is recommended to run full tests on real device, for eaach platform:

- latest mass market device with most used OS version
- low end device with poor performance and minimum supported OS version

UI tests for different screen sized and compatibility tests for less used OS versions can be performed on emulators.

Performance should be executed on real devices (most popular + weakest). Test includes working with large amount of data (10K+ records), battery usage, memory leak checks

Real devices, OS versions, screen sized to test better to be selected based on analytics. In case it is not available market stats can be used, e.g. for Austria:

- <https://gs.statcounter.com/os-version-market-share/android/mobile-tablet/austria>
- <https://gs.statcounter.com/os-version-market-share/ios/mobile-tablet/austria>
- <https://gs.statcounter.com/vendor-market-share/mobile/austria>
- <https://gs.statcounter.com/screen-resolution-stats/mobile/austria>

###6 Localization testing
- Check localization files if all keys present
- Check encoding is correct for each language in application
- Find longest translations for each key, test how localized text displayed on smaller screens
- Check locales for dates, numbers display
- Special attention for right to left languages (Arabic)
- Automated tests with taking screenshots help lot in this task

###7 Multiuser/device access to data
- Bring 2-3 devices with the same account offline
- Add/delete records on devices
- Add/delete/merge acccounts on devices
- Bring devices back online, check merging data was done according to requirements

###8 Version upgrades testing
- if backup file structure was changed in previous version X, change upgading application from version X-1 to current version. Data should be migrated correctly
- check upgrading to payed version

###9. Adherence to Apple Human Interface Guideline and Android Design Guidelines, Accessibility testing
- Should be executed with high priority during application development.
- in case version is live, can be tested with lower priority

##II. Testing priorities:

Testing priority varies based on development cycle.

If application is long time on market, then data migration/upgrade, functional regression of business features should be in priority. Most probably performance, mobile specific, comatibility, UI issues already solved will be executed with lower priority.
 
Analitics is good source to understand most used features, languages, devices and focus on it.

History of previous hot fixes and backlog analisys also good hint for areas to look at.


# Tasks 2 and 3

**Automation startegy**

- according to testing pyramid most efforts should be spent for unit, component tests, less on API tests and UI e2e tests
- advantage of automated tests is finding problems eralier, therefore they should be integrated into CI
- UI tests are much slower and prone to random failures than API tests, for CI it is better to run all API tests and smoke/critical path UI tests
- UI tests for edge cases or regression can be executed during night with re-running failed tests in the morning and checking failed tests manually
- UI usually take more time to implement/debug/troubleshoot. Therefore they should have lower priority

**Automating API tests**

- For simple and not often changing APIs such tools as Postman, ReadyAPI, SoapUI can be used. They have low entry threshold, less experienced QA engineers can design and run tests.
- If API models/methods are changing, complicated, can be called as part of E2E scenarios, QA engineers have programming skills - better to use automation frameworks.

**Automating UI tests**

- better to use recognized frameworks which have good support. E.g. Ranorex, UIAutomator,  Appium

**Proposed test cases**

Due to limited time I automate next test cases for API:

- get Product by Id: assert field values returned for existing product (excluding categories)
- get Product by Id: check server response when sending incorrect or not exising Id: -1, huhe number, text
- add Product: check field validations for 'name' field: length (min, min-1, max, max+1), empty field, field with special symbols, error messages coming from server
- delete product by id: create and delete product, try to delete product with not existing id
- get Product: check by default 10 first products returned, check 1 positive scenario for limit and skip parameters
- PATCH tests and het Products negative tests not implemented

Test cases for UI:

- check accounts search on main screen (filtering)
- click on some accounts from main menu

**Proposed solution**

- Java framework
- TestNG
- Appium for UI tests
- Apache HTTP client for API tests

3 "low level" drivers in rtn26hcdrivers package to implement methods that can be used by next layers:

- HttpDriver: apache http client, allows sending GET/POST/DELETE/PUT/PATCH requests with default headers and optional body. Response is stored in response class object that can be parsed/asserted later in tests
- MobileDriver: allows clicking starting/closing application, simple methods to click, type text, get text, get attribute. No waitForElement methods implemented
- ObjectMap class that is used to store element locators + logical names for logging
- DataDriver: allows logging to console, simple assertions, random numbers/strings generation
- RTn26Base class inits drivers and reads environment variabled. Next layers extend this class

bestbuyentities package:

- ProductObject that allows parsing and creating JSON for Products
- Product class that uses HttpDriver instance "http" and DataDriver instanse "data" to execute Products methods to API, parses response and errors that can be asserted later, generates random Product
- CategoryObject - not used yet

rtn26task3 package:

- File with ProductApiTests. Uses Products methods + DataDriver instance data for assertions. Test steps written in code and also logged during execution in the way mid-level QA can read.

gnucashapp package:

- HeaderMapping class with app header elements locators
- Header class with methods to work with Header elements
- AccountListMapping class with account card details in list. Locators returned by card position index in list
- AccountList class allows finding card by account name, clicking card, checking account details

rtn26task3 package:

- 2 tests that use Header and AccountList class methods
- BeforeTest TestNG annotation for method that starts mobile driver with desired capabilities (from environment properties file)
- AfterTest TestNG annotation for method that closes app

Compiling and running tests:

- maven loads all needes libraries and compiles application
- 2 test suites (xml-files) are for mobile and api tests are located in testsuites folder
- testapi and testmobile batch files run tests using suites. Project is ready to CI integration, e.g. to be started by Jenkins
- in \src\test\resources\environment folder properties file located. File name can be used as parameter in test suites to change execution environment
- more details in readme.txt file