

const rowInput=document.getElementById('rows-count');
const inputColumnTable=document.getElementById('input-column-table');
const generateTable=document.getElementById('generate-table');
const generateLayout=document.getElementById('generate-layout');
const outputContainer1=document.getElementById('output-container1');
const outputContainer2=document.getElementById('output-container2');



const removepreviousChildNodes=()=> {
    while(inputColumnTable.childNodes.length) {
        inputColumnTable.removeChild(inputColumnTable.childNodes[0]);
    }
}

const removePreviousLayout=()=> {
    while(outputContainer1.childNodes.length) {
        outputContainer1.removeChild(outputContainer1.childNodes[0])
    }

    while(outputContainer2.childNodes.length) {
        outputContainer2.removeChild(outputContainer2.childNodes[0])
    }
}


const generateInputColumnTable=()=> {

    removepreviousChildNodes();

    removePreviousLayout();

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



const generateLayoutFromTable=()=> {

    removePreviousLayout()

    const rowsCount=parseInt(rowInput.value)


    let leftBlockMax=0;
    for(let row=0;row<rowsCount;row++) {
        const block1Count=document.getElementById('input1-'+(row+1)).value;
        leftBlockMax=Math.max(block1Count, leftBlockMax);
    }

    let rightBlockMax=0;
    for(let row=0;row<rowsCount;row++) {
        const block2Count=document.getElementById('input2-'+(row+1)).value;
        rightBlockMax=Math.max(block2Count, rightBlockMax);
    }

    for(let row=0;row<rowsCount;row++) {

        const block1Count=document.getElementById('input1-'+(row+1)).value;
        const radius = 400-row*50;  

        for (let i = 0; i <block1Count ; i++) {
           // -175 to -105 deg
            const centerX = 400;
            const centerY = 700; 
            const angle = (-Math.PI/2) * (i / (leftBlockMax - 1)) - (Math.PI/2); 
            const x = centerX + radius * Math.cos(angle) - 20; 
            const y = centerY + radius * Math.sin(angle) - 20; 

            const box = document.createElement("div");
            box.classList.add("box");
            box.textContent = block1Count-i;
            box.style.left = `${x}px`;
            box.style.top = `${y}px`;

            box.style.transform = `rotate(${angle * (180 / Math.PI) + 90}deg)`;
            outputContainer1.appendChild(box);
            
        }

        const block2Count=document.getElementById('input2-'+(row+1)).value;
        for (let i = 0; i < block2Count; i++) {
           // -5 to -85 deg

           const centerX = 500;
            const centerY = 700; 
            const angle = (Math.PI/2) * (i / (rightBlockMax - 1)) - (Math.PI/2);
            const x = centerX + radius * Math.cos(angle) - 20; 
            const y = centerY + radius * Math.sin(angle) - 20; 

            const box = document.createElement("div");
            box.classList.add("box");
            box.textContent = i + 1;
            box.style.left = `${x}px`;
            box.style.top = `${y}px`;

            box.style.transform = `rotate(${angle * (180 / Math.PI) + 90}deg)`;
            outputContainer2.appendChild(box);
            
        }

    }
    
}



generateTable.addEventListener('click',generateInputColumnTable);
generateLayout.addEventListener('click',generateLayoutFromTable);



