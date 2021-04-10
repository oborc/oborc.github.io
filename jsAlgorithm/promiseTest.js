var test = new Promise((resolve,regect)=>{
	console.log('new promise');
	// resolve("resolve data"); 
});
test.then((data)=>{
	console.log(data);
	console.log('then');
}).catch(()=>{
	console.log('error');
})
