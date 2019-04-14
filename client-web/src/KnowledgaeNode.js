import React from 'react';
import Component from "./component/Component";
import TextInput from "./component/TextInput";
import OutlineButton from "./component/OutlineButton";

export default class KnowledgaeNode extends Component {
    constructor(props) {
        super(props);
        this.state = {
            relationships : []
        }

        this.addRelationship = this.addRelationship.bind(this);
        this.submit = this.submit.bind(this);
    }

    render() {
        let size = 'col-sm-' + (this.props.size || 6);
        let components = [];
        for (let i in this.state.relationships) {
            let r = this.state.relationships[i];
            components.push(<div className="input-group mb-3">
                <input onBlur={this.updateRelationshipName(i)} type="text" className="form-control" aria-label="Title"
                       aria-describedby="basic-addon1"/>
            </div>);
        }
        components.push(<button onClick={this.addRelationship} type="button" className="btn btn-outline-primary">Add relationship</button>);
        return (
            <div className={size}>
                {components}
                <button onClick={this.submit} type="button" className="btn btn-success">Submit</button>
            </div>
        )
    }

    addRelationship() {
        let oldSection = this.state.relationships;
        oldSection.push({name : '', to : ''});
        this.setState({relationships : oldSection});
    }

    submit() {
        console.log(this.state);
    }

    updateRelationshipName(i) {
        return (e) => {
            this.state.relationships[i].name = "Value";
            this.setState();
        }
    }

}