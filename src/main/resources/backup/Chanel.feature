@Chanel
Feature: Chanel Demo Scenarios

  @ChanelScanQRKakao
  Scenario: QR scan for KakaoTalk
    Given I try to close application by name "KakaoTalk" and ignore error
    And I start device logging
    And I start device vital monitoring every "3" seconds
    And I click on "droid.bttn.while_using_the_app"
    And I audit accessibility on page "QRScan"
    And I start inject "Public:QR.png" image to application name "QR & Barcode Scanner"
    And I audit accessibility on page "OpenURL"
    And I click on "qrscanner.link"
    When I wait for "5" seconds
    Then I should see text "Welcome to KakaoTalk"
    And I enter "jmong@perforce.com" to "kakao.user"
    And I enter "12345678" to "kakao.pass"
    And I audit accessibility on page "KakaoLogin"
    And I click on "kakao.login"
    And I stop device vitals
    And I stop device logging

  @ChanelScanQRWechat
  Scenario: QR scan for Wechat
    Given I try to close application by name "Wechat" and ignore error
    And I click on "droid.bttn.while_using_the_app"
    And I start inject "Public:QR.png" image to application name "QR & Barcode Scanner"
    And I click on "qrscanner.link"
    When I wait for "3" seconds
    Then I should see text "Welcome to Wechat"
    And I enter "jmong@perforce.com" to "wechat.user"
    And I enter "12345678" to "wechat.pass"
    And I click on "wechat.login"
