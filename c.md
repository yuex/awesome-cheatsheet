# C

For gcc, always use following flags to compile, make it an alias to your .rc

    gcc -Wall -Wextra -std=c99 -pedantic

Print line num of code

    fprintf(stdout, "%s\n", __LINE__)
