import React from 'react';
import Component from "./component/Component";
import DataBrowser from "./component/DataBrowser";
import KnowledgaeNode from "./KnowledgaeNode";

export default class DashBoardDataSection extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <KnowledgaeNode/>
            </div>
        )
    }

}