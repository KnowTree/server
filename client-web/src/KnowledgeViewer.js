import React from 'react';
import Component from "./component/Component";

export default class KnowledgeViewer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            groups : [],
            selected_group : null,

        }
    }

    render() {
        let groupList = this.buildGroupList();
        let explorer = this.buildExplorer();
        return (
            <div>
                <div className="col-md-4">
                    {groupList}
                </div>
                <div className="col-md-8">
                    {explorer}
                </div>
            </div>
        )
    }

    buildGroupList() {
        let items = '';
        for (let i in this.state.groups) {
            let group = this.state.groups[i];
            let className;
            if (i === this.state.selected_group) {

            } else {

            }
            items += <li className="list-group-item" onClick={this.groupItemClick(i)}>{group.name}</li>
        }
        return (
            <ul className="list-group">
                {items}
            </ul>
        )
    }

    buildExplorer() {
        if (this.state.selected_group) {

        } else {
            return (
                <p>No content</p>
            )
        }
    }

    groupItemClick(index) {
        return (e) => {
            this.setState({
                selected_group : index
            })
        }
    }
}