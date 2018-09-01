Feature: the IP information can be retrieved
  Scenario: client makes call to GET IP information
    When the client calls /
    Then the client receives response status code of 200
    And the client receives body "TalkTalk"

  Scenario: client makes call to GET IP information for BT
    When the client set the remote IP as 1.1.1.1
    When the client calls /
    Then the client receives response status code of 200
    And the client receives body "BT"