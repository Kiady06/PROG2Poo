const passedGrade = (grade) => {
    return grade >= 10;
}

export const getRetakeExams = (student) => {
    const rslt = [];

    for (let grade in student.grades) {
        if (!Number.isFinite(student.grades[grade])) {
            continue;
        }
        if (!passedGrade(student.grades[grade])){
            rslt.push(grade);
        }
    }

    return rslt;
};