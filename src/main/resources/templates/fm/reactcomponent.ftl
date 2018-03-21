import React from 'react';

${imports}

class ${label#stripWhiteSpace} extends React.Component {
   render() {
      return (
      		${childComponent}
      );
   }
}
export default ${label#stripWhiteSpace};