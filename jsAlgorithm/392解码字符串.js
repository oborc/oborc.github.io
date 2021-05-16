/**
 * @param {string} s
 * @return {string}
 */
var decodeString = function(s) {
    let stack = [];
    for(let i=0;i<s.length;i++){
        if(s[i]!=']'){
			if(isNaN(Number(s[i]))){
				stack.push(s[i]);
			}else{
				stack.push(Number(s[i]));
			}
        }
        if(s[i] == ']'){
            let top = stack.pop();
            while(stack[stack.length-1]!='['){
                top = top + stack.pop();
            }
			stack.pop();
            let times = stack.pop();
			let add = top;
            for(let time = 1;time < times;time++){
                top += add;
            }
			stack.push(top);
        }
    }
	console.log(stack);
	for(let i in stack){
		stack[i] = stack[i].split("").reverse().join("");
	}
    return stack.join("");
};
// decodeString("3[a]2[bc]");
// decodeString("3[a2[c]]");
decodeString("100[leetcode]");