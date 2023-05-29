def fib(n):
    if(n <= 1):
        ans = 1
    if(n > 1):
        b = n - 1
        c = n - 2
        x = fib(b)
        y = fib(c)
        ans = x + y
    return ans
ans = fib(0)
print(ans)
ans = fib(1)
print(ans)
ans = fib(2)
print(ans)
ans = fib(3)
print(ans)
ans = fib(6)
print(ans)
ans = fib(10)
print(ans)
