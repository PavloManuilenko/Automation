Story: checking positions of coaches on team page

Narrative:
As a pupil
I want to go on page of team with all coaches and verify their positions

Scenario: verifying position for coaches
Given Navigate to Team page from Home page
Then <coach> has position <position>

Examples:
|coach|position|
|Артем Карпов|тренер|
|Денис Питиряков|тренер|
|Александр Галковский|тренер, PSM, OCA|

