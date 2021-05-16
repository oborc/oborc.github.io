function find(arry,num){
	let start = 0;
	let end = arry.length;
	let middle = Math.ceil((start+end)/2);
	while(start<=end){
		if(arry[middle] == num){
			return middle;
		}
		if(arry[middle] > num){
			end = middle-1;	
		}
		if(arry[middle] < num){
			start = middle+1;
		}
		middle = Math.ceil((start+end)/2);
	}
	return "not found";
}
console.log(find([1,4,6,7,9],4));
console.log(find([1,4,6,7,9],5));
console.log(find([1,4,6,7,9,10],5));
console.log(find([1,4,6,7,9,10],10));