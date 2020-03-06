ThrededURLVisitor for Katalon Studio
==============

Run `Test Cases/THREAD/TC1_simpleVisit`, you will see multipe Chrome windows are opened.

| Number of threads | seconds taken by  |
| :--- | ----: |
| 1 thread  | 71 secs |
| 2 threads | 71 secs |
| 3 threads | 65 secs |
| 4 threads | 69 secs |

As this shows, the TC1_simpleVisit script took around 70 seconds. 
Increasing the number of threads in the script does not make the script faster.

I looked at the 4 windows opened on the desktop to see why. 
I found that only one window among the 4 is active at a time, and other windows stand still.
The active window switches, but it never happens that 2 or more windows are active simultaniously.
It seemes that Chrome browser works as a single process with 4 windows attached, and
only 1 of 4 moves at a time.

Interesting and a bit disappointing finding.
