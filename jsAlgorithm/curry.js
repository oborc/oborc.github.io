function curry(fn){
	// console.log(fn.length);
	// console.log(arguments);
	const ctx = this;
	return function inner(...args){
		console.log(arguments);
		if(arguments.length == fn.length){
			// console.log('this is inner this:'+this);
			return fn.call(ctx,...args); // 返回结果
		}else{
			// console.log('this is inner ctx:' + ctx);
			return inner.bind(ctx,...args); // 返回当前inner函数，并把参数带回
		}
	};
}
function add(x,y){
	return x+y;
}
currAdd = curry(add);

console.log("======================");
console.log(currAdd(1)(2));
console.log(currAdd(1,2));
console.log(currAdd(1);