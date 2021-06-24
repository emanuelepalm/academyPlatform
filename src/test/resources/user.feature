Feature: user certification
  Rule: Paolo ha bisogno di più di 60


  Scenario: user is passed
    Given that the user Paolo is given a task to clear TA certification exam
    When Paolo got 98 marks in exam
    Then Paolo is known as TA certified

  Scenario: user is not passed
    Given that the user Paolo is given a task to clear TA certification exam
    When Paolo got 33 marks in exam
    Then Paolo is not known as TA certified

    Scenario Outline: Parametrized Test
      Given that the user <userName> is given a task to clear <subject> certification exam
      When <userName> got <mark> marks in exam
      Then <userName> is known as <subject> certified
      Examples:
        | userName| subject | mark |
        | Andrea  | TA      | 61   |
        | Mario   | TA      | 48   |
        | Gennaro | TA      | 90   |
        | Pippo   | TA      | 59   |

      Scenario: Esempio data table
        Given that more users take a subject exam
        |ndonio|TA      | 66   |
        |pippo |TA      | 66   |
        |mario |TA      | 50   |
        |luca  |TA      | 50   |
        |gennaro|TA      | 50   |
        Then only 2 users have passed the exam

    Scenario: Esempio data table 2
      Given that more users take a subject exam
        |ndonio|TA      | 66   |
        |pippo |TA      | 66   |
        |mario |TA      | 66   |
        |luca  |TA      | 66   |
        |gennaro|TA      | 66  |
      Then only 5 users have passed the exam
