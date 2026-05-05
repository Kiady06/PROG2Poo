import { expect } from "chai";
import { describe, it } from "mocha";
import { isAboveAvg } from "./index.js"

describe ("Above", () => {
    it("should return true if we ask [5, 5, 5] with 6", () => {
        expect(isAboveAvg([5,5,5], 6)).to.eq(true);
    });
    it("should return false if we ask [5, 5, 5] with 4", () => {
        expect(isAboveAvg([5,5,5], 4)).to.eq(false);
    });
    it("should return false if we ask [5, 5, 5] with 5", () => {
        expect(isAboveAvg([5,5,5], 5)).to.eq(false);
    });
     it("should throw an error if at least one of the args are missing", () => {
        expect(() => isAboveAvg()).to.throw("missing args");
    });
    it("should return true if array is null", () => {
        expect(isAboveAvg([], 6)).to.eq(true);
    });
})