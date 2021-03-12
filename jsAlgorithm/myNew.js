function person(name,age){
	this.name = "张三";
	this.age = 20;
	if(name){
		this.name = name;
	}
	if(age){
		this.age = age;
	}
}

Object.prototype.myNew = function(constructor,args){
	let o = Object.create(constructor.prototype);
	constructor.apply(o,args);
	return o;
}

console.log("var originNew = new Person():")
var originNew = new person();
console.log(originNew);
console.log('person.prototype.isPrototypeOf(originNew)');
console.log(person.prototype.isPrototypeOf(originNew));

console.log('new person("李四",20)');
var originNew2 = new person("李四",20);
console.log(originNew2);
console.log('person.prototype.isPrototypeOf(originNew2)');
console.log(person.prototype.isPrototypeOf(originNew2));



console.log('Object.myNew(person,["李四",20])');
var myNew = Object.myNew(person);
console.log(myNew);
console.log('person.prototype.isPrototypeOf(myNew)');
console.log(person.prototype.isPrototypeOf(myNew));

console.log('Object.myNew(person,["李四",20])');
var myNew2 = Object.myNew(person,["李四",20])
console.log(myNew2);
console.log('person.prototype.isPrototypeOf(myNew2)');
console.log(person.prototype.isPrototypeOf(myNew2));

/** 
 * new 操作相当于创建一个person实例，根据js基础可得知：
 * 一个实例的__proto__属性指向生成该实例的构造函数的原型对象。
 * 则需要验证myNew 构造出的实例 是否在构造函数的原型链上。
 * 可用js提供的API Object.prototype.isPrototypeOf(instance)来判断
 */
