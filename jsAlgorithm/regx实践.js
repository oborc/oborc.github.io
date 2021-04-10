function format(number){
	let numArray = number.toString().split("");
	return numArray.reduce(function(pre,next,index){
		return ((index%3 != 0) ? next:(next+',')) + pre
	})
}
console.log(format(1234));
console.log(format(12345));
console.log(format(1234567));