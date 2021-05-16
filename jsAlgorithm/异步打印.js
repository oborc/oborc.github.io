async function async1(){
	console.log("async1 start"); // 
	await async2(); 
	await async3(); 
	console.log("test await");
}
/**
 * 这个方法是上面async1 的等量替换方法
 */
function async1Gener(){
	console.log("async1 start");
	new Promise(function(resolve){
		console.log("async2 ");
		resolve();
		}).then(function(){
			new Promise(function(resolve){
				console.log("async3")
				resolve();
			}).then(function(){
				console.log("test await")
			});
		})
}
async function async2(){
	console.log("async2");
}
async function async3(){
	console.log("async 3");
}
console.log("script start"); // 
setTimeout(function(){
	console.log("setTimeOut"); // 
},0)

// async1();
async1Gener();
new Promise(function(resolve){
	console.log("promise 1"); // 
	resolve();
}).then(function(){
	console.log("promise2");//
})
console.log("script end"); // 

