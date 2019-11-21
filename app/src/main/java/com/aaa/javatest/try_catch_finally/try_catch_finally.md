
```
finally中有return必定返回，无视try、catch中的return
1 [try]
3 [try, finally]
4 [new]
testList=[new]
```

```
finally中无return，则finally会改变暂存的内容，但不能改变该对象的引用
1 [try]
3 [try, finally]
4 [new]
testList=[try, finally]
```