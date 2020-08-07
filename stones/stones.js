const readlineSync = require("readline-sync");
console.log("Please input a number between 1 and " + Number.MAX_SAFE_INTEGER);
let x = Number(readlineSync.question("Stones = "), 10);
let y = Number(readlineSync.question("Beetles = "), 10);

if (x == x + 1 || y == y + 1) {
  console.log("Please input a number between 1 and " + Number.MAX_SAFE_INTEGER);
  process.exit(0);
}
if (!(Number.isInteger(x) && Number.isInteger(y))) {
  console.log("Please input integer");
  process.exit(0);
}
if (x == 0 || y == 0 || x < y) {
  console.log("Please input correct values");
  process.exit(0);
}
let length = intervalLength(x, y);
let left = Math.trunc((length - 1) / 2);
let right = length - 1 - left;

console.log("Result : " + left + "," + right);
/*
  Функция вычисляет размер наибольшего возможно участка на который может сесть последний ( y ) жук
 */
function intervalLength(x, y) {
  if (y == 1) {
    return x;
  } else {
    let elementPow = Math.log2(y); // в каком ряду степени 2 находится y
    let minPow = Math.trunc(elementPow); // минимальный элемент в этом ряду
    result = x;
    /*
     Функция расчитывает коэффициент целая часть которого указывает на минимально возможную длинну участка,
      а дробная часть на колличество максимально возможных участков для этой итерации
    */
    for (let j = 0; j < minPow; j++) {
      result = (result - 1) / 2;
    }
    if (Number.isInteger(elementPow)) {
      return Math.ceil(result);
    } else {
      let firstElement = Math.pow(2, minPow);
      let maxPow = minPow + 1;
      let lastElement = Math.pow(2, maxPow);
      let intervalWeight = result % 1; // Коэффициент веса ряда
      let elemWeight = (y - firstElement + 1) / (lastElement - firstElement); // Коэффициент веса элемента
      if (elemWeight <= intervalWeight) {
        return Math.ceil(result);
      } else {
        return Math.trunc(result);
      }
    }
  }
}
