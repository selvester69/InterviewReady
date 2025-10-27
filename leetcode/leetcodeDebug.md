# leetcode 150

## H-index

### Debug Trace: `hIndex` Method

### **Scenario 1: Positive Case**

Input: `citations = [3, 0, 6, 1, 5]`, `n = 5`

1. **Initialize `counts` array:**
    `counts = [0, 0, 0, 0, 0, 0]`

2. **Populate `counts`:**
    * `i = 0`, `citations[0] = 3`
        `counts = [0, 0, 0, 1, 0, 0]`
    * `i = 1`, `citations[1] = 0`
        `counts = [1, 0, 0, 1, 0, 0]`
    * `i = 2`, `citations[2] = 6`
        `counts = [1, 0, 0, 1, 0, 1]`
    * `i = 3`, `citations[3] = 1`
        `counts = [1, 1, 0, 1, 0, 1]`
    * `i = 4`, `citations[4] = 5`
        `counts = [1, 1, 0, 1, 0, 2]`

3. **Line Sweep:**
    * `j = 4`
        `counts = [1, 1, 0, 1, 2, 2]`
    * `j = 3`
        `counts = [1, 1, 0, 3, 2, 2]`
        Condition `counts[3] >= 3` is met.
        **Return 3.**

---

### **Scenario 2: Fail Case**

Input: `citations = [0, 0, 0]`, `n = 3`

1. **Initialize `counts` array:**
    `counts = [0, 0, 0, 0]`

2. **Populate `counts`:**
    * `i = 0`, `citations[0] = 0`
        `counts = [1, 0, 0, 0]`
    * `i = 1`, `citations[1] = 0`
        `counts = [2, 0, 0, 0]`
    * `i = 2`, `citations[2] = 0`
        `counts = [3, 0, 0, 0]`

3. **Line Sweep:**
    * `j = 2`
        `counts = [3, 0, 0, 0]`
    * `j = 1`
        `counts = [3, 0, 0, 0]`
    * `j = 0`
        `counts = [3, 0, 0, 0]`
        Condition `counts[0] >= 0` is met.
        **Return 0.**
