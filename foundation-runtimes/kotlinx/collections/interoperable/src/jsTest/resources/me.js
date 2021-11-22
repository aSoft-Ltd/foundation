export const = {
    name: "Anderson",
    greet: function () {
        return "Hello Lamax";
    },
    doStuff: function(iterable) {
        const arr = []
        for(const obj of iterable) {
            arr.push(obj)
        }
        return arr;
    },
    convertToArray: function(list) {
        return list.toArray();
    },
    addUnbounded: function(calc,number) {
        console.log(Object.assign({},calc))
        const { plus } = calc
        console.log(plus)
        return plus(number)
    }
}