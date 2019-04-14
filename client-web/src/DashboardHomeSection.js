import React from 'react';
import Component from "./component/Component";
import KnowledgeViewer from "./KnowledgeViewer";

export default class DashboardHomeSection extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <KnowledgeViewer/>
            </div>
        )
    }

}