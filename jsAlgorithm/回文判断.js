function judgeHW(str){
	let right = str.length-1;
	let left = 0;
	while(left <= right){
		if(str.charAt(left) == str.charAt(right)){
			left++;
			right--;
		}else{
			break;
		}
	}
	if(left > right){
		return true;
	}else{
		return false;
	}
}

console.log(judgeHW("abccba"));
console.log(judgeHW("123"));
console.log(judgeHW("121"));

/**
 * @param {Object} str枚举当前字符串内的所有回文串
 */
function enumHW(str){
	
}