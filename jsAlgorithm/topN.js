/**
 * topN 一般用堆排序会快一些，
 * js 中没有堆的结构，所以用快速排序先实现一遍
 * @param {Object} arr
 * @param {Object} n
 */
function topN(arr,n){
	let start = 0;
	let end = arr.length;
	
}

function quickSort(arr,low,high){
	let key = arr[low];
	let start = low;
	let end = high;
	if(start<end){
		let key = arr[start];
		while(start!=end){
			while(start<end && arr[end] > key){
				j--;
			}
			arr[start] = arr[end];
			while(start<end && arr[start] < key){
				start++;
			}
			arr[end] = arr[start];
		}
		arr[start] = key;
		quickSort(arr,low,start-1);
		quickSort(arr,start+1,high);
	}

}

var kk = [1,3,5,3,6,4];

console.log(quickSort(kk,0,kk.length));