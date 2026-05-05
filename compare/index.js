const addTab = (tab) => {
    let sum = 0
    tab.forEach(element => {
        sum += element;
    });

    return sum;
};

const avg = (tab) => {
    return addTab(tab) / tab.length;
};

export const isAboveAvg = (tab, ref) => {
    
    if (tab == null || ref == null) {
    throw Error("missing args");
}
    if (tab == null && ref !== null || tab == null && ref !== undefined ) {return true}
    
    const avgClass = avg(tab);
    if (avgClass >= ref) {
        return false;
    } else {
        return true;
    }
};