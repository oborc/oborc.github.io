// function deepClone(obj){
// 	if(obj == null){
// 		return obj;
// 	}
	
// 	// if( typeof objItem == 'function'){
// 	// 	return ;
// 	// }
// 	let result = {};
// 	if(typeof obj == 'object'){
// 		if(Array.prototype.isPrototypeOf(obj)){
// 			result =  [...obj];
// 		}else{
// 			for (let i in obj) {
// 			    result[i] = deepClone(obj[i]);
// 			}
// 		}
// 		return result;
// 	}
// }


function deepClone(obj) {
    if (typeof obj != 'object') {
        throw new Error('obj 不是一个对象！')
    }
    let isArray = Array.isArray(obj)
    let cloneObj = isArray ? [...obj] : { ...obj }
    Reflect.ownKeys(cloneObj).forEach(key => {
        cloneObj[key] = (typeof obj[key] == 'object') ? deepClone(obj[key]) : obj[key]
    })
    return cloneObj
}
var data = {
	array:[1,2,3,4],
	obj:{
		value1: "value1",
		value2:[3,4,5],
		objIn:{
			objInner: "ae",
			objSet: new Set("set")
		}
	}
}
console.log(data);
console.log(deepClone(data));