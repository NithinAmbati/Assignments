

const rowInput=document.getElementById('rows-count');
const inputColumnTable=document.getElementById('input-column-table');
const generateTable=document.getElementById('generate-table');
const generateLayout=document.getElementById('generate-layout');
const outputContainer=document.getElementById('output-container');


const removepreviousChildNodes=()=> {
    while(inputColumnTable.childNodes.length) {
        inputColumnTable.removeChild(inputColumnTable.childNodes[0]);
    }
}


const generateInputColumnTable=()=> {

    removepreviousChildNodes();

    const rowsCount=parseInt(rowInput.value)
    console.log(rowsCount)
    for(let i=0;i<rowsCount;i++) {
        const rowElement=document.createElement('tr');
        const rowNumberElement=document.createElement('td');
        const block1=document.createElement('td');
        const block2=document.createElement('td');
        const inputElement1=document.createElement('input');
        const inputElement2=document.createElement('input');
       
        rowNumberElement.innerText='R'+(i+1);
        inputElement1.setAttribute('type','text');
        inputElement2.setAttribute('type', 'text');
        inputElement1.setAttribute('id' , 'input1-'+(i+1));
        inputElement2.setAttribute('id' , 'input2-'+(i+1));

        block1.appendChild(inputElement1);
        block2.appendChild(inputElement2);
        rowElement.appendChild(rowNumberElement);
        rowElement.appendChild(block1);
        rowElement.appendChild(block2);
        inputColumnTable.appendChild(rowElement);
    }
}


const generateLayoutTable=()=> {
    const rowsCount=parseInt(rowInput.value)

    const block1Container=document.createElement('div');
    const block2Container=document.createElement('div');

    block1Container.classList.add('block1-container');
    block2Container.classList.add('block2-container');

    for(let i=0;i<rowsCount;i++) {
        
        const subBlockContainer1=document.createElement('div');
        const subBlockContainer2=document.createElement('div');

        subBlockContainer1.classList.add('sub-block-container1');
        subBlockContainer2.classList.add('sub-block-container2');

        const block1Count=document.getElementById('input1-'+(i+1)).value;
        const block2Count=document.getElementById('input2-'+(i+1)).value;
        
        for(let i=0;i<block1Count;i++) {
            const block1Element=document.createElement('div');
            block1Element.classList.add('block1');
            block1Element.setAttribute('class', 'box');
            //block1Element.style.transform=`rotate(${rotateValue}deg)`;

            const x=(block1Count-i)*10;
            const y=(block2Count-i)*10;
            block1Element.style.top=x;
            block1Element.style.left=y;

            block1Element.innerText=i+1;
            subBlockContainer1.appendChild(block1Element);
        }
        
        for(let i=0;i<block2Count;i++) {
            const block2Element=document.createElement('div');
            block2Element.classList.add('block2');
            block2Element.setAttribute('class', 'box');
            block2Element.innerText=i+1;
            subBlockContainer2.appendChild(block2Element);
        }

        block1Container.appendChild(subBlockContainer1);
        block2Container.appendChild(subBlockContainer2);
    }

    outputContainer.appendChild(block1Container);
    outputContainer.appendChild(block2Container);

}




generateTable.addEventListener('click',generateInputColumnTable);
generateLayout.addEventListener('click',generateLayoutTable);
