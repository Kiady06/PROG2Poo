import { normalize } from "./import.js";

const isIvandry = (input) => {
    return /\bivandry\b/i.test(input);
}

export const getIvandryCount = (...args) => {
    const inputs = normalize(args);

    let count = 0;
    inputs.forEach(el => {
        if (typeof el !== "string")
            throw new Error("Inputs should be strings");
            
        if (isIvandry(el))
            count++;
    });

    return count;
};