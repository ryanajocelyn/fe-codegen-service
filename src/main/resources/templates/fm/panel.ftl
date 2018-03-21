<PanelGroup accordion id="${htmlID}">
	<Panel eventKey="${id}" bsStyle="primary">
		<Panel.Heading>
			<Panel.Title componentClass="h3">${label}</Panel.Title>
		</Panel.Heading>
		<Panel.Body>
			${childComponent}
		</Panel.Body>
	</Panel>
</PanelGroup>