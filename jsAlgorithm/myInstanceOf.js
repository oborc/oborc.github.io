function myInstanceOf(instance,fun){
	let proto = instance.__proto__;
	let funPrototype = fun.prototype;
	while(true){
		if(proto == null){ // 将判空放在前面，避免后面和空对比 || 后面写成 funPrototype == proto
			return false;
		}
		if(proto === funPrototype){
			return true;
		}
		// 不停向上查找
		proto = proto.__proto__;
	}
	
}
var testArray = [];
console.log("var testArray = [];")
console.log("testArray instanceOf Array:");
console.log(testArray instanceof Array);
console.log("myInstanceOf(testArray,Array):")
console.log(myInstanceOf(testArray,Array));

function person(){};
person.prototype = new Array();
var person1 = new person();
console.log("function person(){};\n"+
"person.prototype = new Array();\n"+
"var person1 = new person();")
console.log("person1 instanceof Array")
console.log(person1 instanceof Array);
console.log("myInstanceOf(person1,Array):")
console.log(myInstanceOf(person1,Array));