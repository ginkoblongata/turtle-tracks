
add("These are the logical operations supported by Turtle Tracks.");
add("TRUE returns the boolean value true.");
add("FALSE returns the boolean value false.");
add("NOT returns the logical opposite of the argument. If the argument is not a boolean value, NOT throws an error.");
add("ALLOF returns true if all its arguments are true, or false if any of its arguments are false. If any argument is not a boolean value, ALLOF throws an error.");
add("ANYOF returns true if any of its arguments are true, or false if all of its arguments are false. If any argument is not a boolean value, ANYOF throws an error.");
add("These are the bitwise operations supported by Turtle Tracks.");
add("BITNOT returns the bitwise not of the argument. If the argument is not an integer, BITNOT throws an error.");
add("BITAND returns the bitwise and of the arguments. If an argument is not an integer, BITAND throws an error.");
add("BITOR returns the bitwise or of the arguments. If an argument is not an integer, BITOR throws an error.");
add("BITXOR returns the bitwise exclusive or of the arguments. If an argument is not an integer, BITXOR throws an error.");
add("LSHIFT shifts the bits of the first number left by the second number. To shift right, pass a negative number as the second argument. 0's shifted into all vacated digits. If an argument is not an integer, LSHIFT throws an error.");
add("ASHIFT shifts the bits of the first number left by the second number. To shift right, pass a negative number as the second argument. ASHIFT preserves the sign of the number. That is, if the number is being shifted to the right, and the high-order bit of the original number is a 1, then 1's are shifted into the left end. If the high-order bit of the original number is a 0, then 0's are shifted into the left end. If an argument is not an integer, ASHIFT throws an error.");
add("These are the numeric comparison operations supported by Turtle Tracks.");
add("EQUAL? returns true if and only if the arguments are equal. If both arguments are numbers, EQUAL? returns numeric equality, otherwise, it returns string equality or list equality.");
add("GREATER? returns true if and only if expr is numerically greater than expr2. If an argument is not a number, GREATER? throws an error.");
add("LESS? returns true if and only if expr is numerically less than expr2. If an argument is not a number, LESS? throws an error.");
add("GREATEREQUAL? returns true if and only if expr is numerically greater than or equal to expr2. If an argument is not a GREATEREQUAL, GREATER? throws an error.");
add("LESSEQUAL? returns true if and only if expr is numerically less than or equal to expr2. If an argument is not a number, LESSEQUAL? throws an error.");
add("Turtle Tracks includes a full set of arithmetic functions.");
add("INTEGER returns the integer portion of the argument. If the argument is not a number, INTEGER throws an error.");
add("ROUND rounds the argument to the nearest integer. If the argument is not a number, ROUND throws an error.");
add("ABS returns the absolute value of the argument. If the argument is not a number, ABS throws an error.");
add("MINUS returns the negation of the argument. If the argument is not a number, MINUS throws an error. Note that there is a precedence difference between MINUS and -.");
add("SUM returns the sum of the arguments. If an argument is not a number, SUM throws an error.");
add("DIFFERENCE returns the difference of the arguments. If an argument is not a number, DIFFERENCE throws an error.");
add("PRODUCT returns the product of the arguments. If an argument is not a number, PRODUCT throws an error.");
add("QUOTIENT divides expr1 by expr2 and returns the quotient. The quotient is a floating-point number if expr1 is not evenly divisible by expr2. If an argument is not a number, or if expr2 is 0, QUOTIENT throws an error.");
add("REMAINDER divides expr1 by expr2 using integer division. The remainder is returned. The quotient is ignored. If an argument is a non-integer value, it is first rounded to the nearest integer. If an argument is not a number, or if expr2 is 0, REMAINDER throws an error.");
add("SQRT returns the square root of the argument. If the argument is not a number, or if it is a negative number, SQRT throws an error.");
add("POWER returns expr1 raised to the power of expr2. If an argument is not a number, or both arguments are zero, or expr1 is negative and expr2 is not an integer, POWER throws an error.");
add("EXP returns the constant <i>e</i> raised to the power of the argument. If the argument is not a number, EXP throws an error.");
add("LOG returns the natrual logarithm (log to the base <i>e</i>) of the argument. If the argument is not a number, or if it is a non-positive number, LOG throws an error.");
add("RANDOM returns a pseudo-random integer between 0 and the given argument minus 1, inclusive. If the argument is not a positive integer, RANDOM throws an error.");
add("RANDOMIZE sets the seed for sequence of pseudo-random numbers to be returned by subsequent calls to RANDOM. Every time Turtle Tracks is started up, the seed is set to 0; therefore, if you restart Turtle Tracks, the same sequence of numbers will be generated. Use RANDOMIZE to set a seed at \"random\" depending on the value of the system time stamp. The version of RANDOMIZE with an argument uses the argument as a seed. If the argument is not an integer, RANDOMIZE throws an error.");
add("Turtle Tracks includes a full set of trigonometric functions.");
add("SIN returns the sine of the argument (given in degrees). If the argument is not a number, SIN throws an error.");
add("COS returns the cosine of the argument (given in degrees). If the argument is not a number, COS throws an error.");
add("TAN returns the tangent of the argument (given in degrees). If the argument is not a number, or if the input value is too close to an odd multiple of 90, TAN throws an error.");
add("RADSIN returns the sine of the argument (given in radians). If the argument is not a number, RADSIN throws an error.");
add("RADCOS returns the cosine of the argument (given in radians). If the argument is not a number, RADCOS throws an error.");
add("RADTAN returns the tangent of the argument (given in radians). If the argument is not a number, or if the argument is too close to an odd multiple of pi/2, RADTAN throws an error.");
add("ARCSIN returns the inverse sine of the argument, in degrees. The returned value will be between -90 and 90. If the argument is not a number, or if it is greater than 1.0 or less than -1.0, ARCSIN throws an error.");
add("ARCCOS returns the inverse cosine of the argument, in degrees. The returned value will be between 0 and 180. If the argument is not a number, or if it is greater than 1.0 or less than -1.0, ARCCOS throws an error.");
add("ARCTAN returns the inverse tangent of the argument, in degrees. The returned value will be between -90 and 90. If the argument is not a number, ARCTAN throws an error.");
add("ARCTAN2 returns the inverse tangent of expry/exprx, in degrees, by finding the angle formed by the points (x,y), the origin, and (1,0). The returned value will be between -180 and 180. ARCTAN2 0 0 is defined as 0. If any of the arguments is not a number, ARCTAN2 throws an error.");
add("RADARCSIN returns the inverse sine of the argument, in radians. The returned value will be between -pi/2 and pi/2. If the argument is not a number, or if it is greater than 1.0 or less than -1.0, RADARCSIN throws an error.");
add("RADARCCOS returns the inverse cosine of the argument, in radians. The returned value will be between 0 and pi. If the argument is not a number, or if it is greater than 1.0 or less than -1.0, RADARCCOS throws an error.");
add("RADARCTAN returns the inverse tangent of the argument, in radians. The returned value will be between -pi/2 and pi/2. If the argument is not a number, RADARCTAN throws an error.");
add("RADARCTAN2 returns the inverse tangent of expry/exprx, in radians, by finding the angle formed by the points (x,y), the origin, and (1,0). The returned value will be between -pi and pi. RADARCTAN2 0 0 is defined to be 0. If any of the arguments is not a number, RADARCTAN2 throws an error.");
add("PI returns an approximation of the value pi. In Turtle Tracks, this value is equal to 3.14159265358979323846.");