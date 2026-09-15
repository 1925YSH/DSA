CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
SELECT DISTINCT salary 
FROM Employee e1 
WHERE N-1 = (
    SELECT COUNT(DISTINCT salary) 
    FROM Employee e2 
    WHERE e2.salary > e1.salary
)
  );
END