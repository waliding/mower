# Mower
The robot like algorithm challenge

## Introduction
This is a brief solution for the mower robot problem.

## Suppositions
Some suppositions were done in order to achieve the job :
+ We can have different grass areas(Area instance is not a Singleton)
+ Mowers collision is ignored
+ No mowers cycle detection
+ Escaping the non recognizable instructions while interpreting a move sequence( Example: GAGAKKGA algorithm will escape  KK

## Interesting things
+ ### Core functions/methods :
    + __CommandInterpreter.toArea__ : is the main one, it maps the instructions input string (File) into an Area object containing the related mowers

    + __Mower.move__ : the core algorithm which interprets the move sequence.

+ ### Mower.move comparison complexity:
    + Omitting the security checks, the algorithm is __O(4n)__ where n is the move sequence length

## Example :
You can simply execute the test : __CommandInterpreter.toArea_interview_dataset()__ 
note that you can modify the input string

## Javadoc :
A javadoc is provided(you can start the index.html file in the javadoc directory)

## N.B :
+ Sources : Not all getters/setters are implemented
+ Tests  : Atomic methods are not tested

Cheers !
