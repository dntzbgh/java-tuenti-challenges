#CHALLENGE 1 : **Super hard sum**
```
Completion time:    10 percentile: 0.11 h
                    Average: 6.95 h
                    90 percentile: 21.19 h
# of completions:   791
```
Your amazing program should calculate the sum if the numbers given in each line, 
and output on line for each question with the response. Numbers can be negative, 
really big and lines contain extra spaces, so make your program resistant to input.
Your program will need to read from standard input, line by line till the end of the input. 
Consider each line a different question. For each line you read, 
output the sum of all the given numbers.
##Sample input
```
2 3

4 5 -1
```
##Sample output
```
5

8
```
# Solution:
The main class (`ChallengeOne`) reads the input file line by line. Each line is parsed
to an array of `BigInteger` and then passed as an argument to a task (`TaskChallengeOne`)
from the threadpool created previously. Each task is responsible for adding all the 
numbers from the array of `BigInteger` that is passed as an argument and then returning it 
to the main program.