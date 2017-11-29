import React from 'react';


class Phototool extends React.Component {
    
    addItem(){
        console.log('addItem');        
    }

    render(){
        const listItems = this.props.list.map((d) => <li key={d}>{d}</li>);
        return <div>
            <span>{this.props.path}</span>
            <ul>{listItems}</ul>
            <button onClick={this.addItem}>add</button>
        </div>;
    }
}

export default Phototool;
