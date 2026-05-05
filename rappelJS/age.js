import { normalize } from "./import.js";

export const countAdults = (...args) => {
    let ages = normalize(args);

    if (ages.length === 0)
        return 0;

    let count = 0;
    for (const age of ages) {
        if (!Number.isInteger(age) || age < 0)
            throw new Error("Input should only contain positive integers");
        
        if (age >= 18)
            count++;
    }

    return count;
}

console.log(countAdults([18, 10, 12, 25, 33]));