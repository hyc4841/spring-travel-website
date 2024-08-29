var toDay = new Date();
let doMonth = new Date(toDay.getFullYear(), toDay.getMonth(), 1); 
let lastDate = new Date(toDay.getFullYear(), toDay.getMonth(), 0);

console.log("doMonth의 값 : ");
console.log(doMonth.getMonth());

console.log("lastDate의 값 : ");
console.log(lastDate.getMonth());

let daysLength = (Math.ceil((doMonth.getDay() + lastDate.getDate()) / 7) * 7) - doMonth.getDay();

console.log(daysLength);
